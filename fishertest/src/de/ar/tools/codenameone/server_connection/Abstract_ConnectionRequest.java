package de.ar.tools.codenameone.server_connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;

public abstract class Abstract_ConnectionRequest extends ConnectionRequest {

	private static final int TIMEOUT = 5000;

	protected boolean isHandled;
	protected boolean hasError;

	private Dialog dialogInifiniteBlocking;

	public Abstract_ConnectionRequest(String url) {
		setPost(true);
		setHttpMethod("POST");
		setUrl(url);
		setTimeout(TIMEOUT);
		setHasError(false);
		isHandled = false;
	}

	@Override
	protected void buildRequestBody(OutputStream os) throws IOException {
		doBuildRequestBody(os, this);
	}

	@Override
	protected void readResponse(InputStream input) throws IOException {
		doReadPesponse(input, this);
		isHandled = true;
	}

	@Override
	protected void handleException(Exception err) {
		super.handleException(err);
		Log.e(err);
		showErrorDialog(err.getMessage());
		setHasError(true);
	}

	@Override
	protected void handleErrorResponseCode(int code, String message) {
		// super.handleErrorResponseCode(code, message);
		Log.p(message);
		showErrorDialog("Http Error " + code + " - " + message);
		setHasError(true);
	}

	public void runRequest() {

		showInfiniteProgress();

		try {
			NetworkManager.getInstance().addToQueue(this);
			// NetworkManager.getInstance().setTimeout(TIMEOUT);
		} catch (Exception e) {
			setHasError(true);
			hideInfiniteProgress();
			showErrorDialog("Error while data connection");
		}
	}

	protected void showInfiniteProgress() {
		Log.p("showInfiniteProgress");
		InfiniteProgress infinitProgress = new InfiniteProgress();
		dialogInifiniteBlocking = infinitProgress.showInifiniteBlocking();
		dialogInifiniteBlocking.show();
		dummyFlushEdt();
	}

	protected void hideInfiniteProgress() {
		Log.p("hideInfiniteProgress");
		if (dialogInifiniteBlocking != null) {
			dialogInifiniteBlocking.dispose();
			dialogInifiniteBlocking = null;
		}
		dummyFlushEdt();
	}

	public static void dummyFlushEdt() {
		Display.getInstance().callSeriallyAndWait(new Runnable() {
			@Override
			public void run() {
				// für Flush
			}
		});
	}

	protected void showErrorDialog(final String errorMsg) {
		Display.getInstance().callSerially(new Runnable() {
			public void run() {
				Dialog.show("Fehler", "Fehler bei Datenverbindung:\n" + errorMsg, "Ok", null);
			}
		});
	}

	public boolean getHasError() {
		return hasError;
	}

	public void setHasError(boolean hasError) {
		this.hasError = hasError;
		if (hasError) {
			kill();
		}
	}

	protected void waitTillRequestWorked() {

		while (!isHandled && !hasError) {
			try {
				Thread.sleep(50);
			} catch (Exception e) {
				// ignore
			}
		}
		hideInfiniteProgress();
	}

	protected abstract void doBuildRequestBody(OutputStream os, Abstract_ConnectionRequest connectionRequest);

	protected abstract void doReadPesponse(InputStream input, Abstract_ConnectionRequest connectionRequest);
}