package sabacc;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ClientProperties {
	private static final String BUNDLE_NAME = "ressource.client"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);

	private ClientProperties() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}

	public static int getInt(String key) {
		try {
			return (new Integer(RESOURCE_BUNDLE.getString(key))).intValue();
		} catch (MissingResourceException e) {
			return 0;
		}
	}

	public static boolean getBoolean(String key) {
		try {
			return (new Boolean(RESOURCE_BUNDLE.getString(key))).booleanValue();
		} catch (MissingResourceException e) {
			return false;
		}
	}
}
