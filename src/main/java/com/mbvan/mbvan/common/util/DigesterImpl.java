package com.mbvan.mbvan.common.util;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.Digester;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

public class DigesterImpl implements Digester {

	private static final boolean _BASE_64 = true;

	private static Log _log = LogFactoryUtil.getLog(Digester.class);

	public String digest(ByteBuffer byteBuffer) {
		return digest(Digester.DEFAULT_ALGORITHM, byteBuffer);
	}

	public String digest(String text) {
		return digest(Digester.DEFAULT_ALGORITHM, text);
	}

	public String digest(String algorithm, ByteBuffer byteBuffer) {
		if (_BASE_64) {
			return digestBase64(algorithm, byteBuffer);
		} else {
			return digestHex(algorithm, byteBuffer);
		}
	}

	public String digest(String algorithm, String... text) {
		if (_BASE_64) {
			return digestBase64(algorithm, text);
		} else {
			return digestHex(algorithm, text);
		}
	}

	public String digestBase64(ByteBuffer byteBuffer) {
		return digestBase64(Digester.DEFAULT_ALGORITHM, byteBuffer);
	}

	public String digestBase64(String text) {
		return digestBase64(Digester.DEFAULT_ALGORITHM, text);
	}

	public String digestBase64(String algorithm, ByteBuffer byteBuffer) {
		byte[] bytes = digestRaw(algorithm, byteBuffer);

		return Base64.encode(bytes);
	}

	public String digestBase64(String algorithm, String... text) {
		byte[] bytes = digestRaw(algorithm, text);

		return Base64.encode(bytes);
	}

	public String digestHex(ByteBuffer byteBuffer) {
		return digestHex(Digester.DEFAULT_ALGORITHM, byteBuffer);
	}

	public String digestHex(String text) {
		return digestHex(Digester.DEFAULT_ALGORITHM, text);
	}

	public String digestHex(String algorithm, ByteBuffer byteBuffer) {
		byte[] bytes = digestRaw(algorithm, byteBuffer);

		return Hex.encodeHexString(bytes);
	}

	public String digestHex(String algorithm, String... text) {
		byte[] bytes = digestRaw(algorithm, text);

		return Hex.encodeHexString(bytes);
	}

	public byte[] digestRaw(ByteBuffer byteBuffer) {
		return digestRaw(Digester.DEFAULT_ALGORITHM, byteBuffer);
	}

	public byte[] digestRaw(String text) {
		return digestRaw(Digester.DEFAULT_ALGORITHM, text);
	}

	public byte[] digestRaw(String algorithm, ByteBuffer byteBuffer) {
		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance(algorithm);

			messageDigest.update(byteBuffer);
		} catch (NoSuchAlgorithmException nsae) {
			_log.error(nsae, nsae);
		}

		return messageDigest.digest();
	}

	public byte[] digestRaw(String algorithm, String... text) {
		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance(algorithm);

			StringBundler sb = new StringBundler(text.length * 2 - 1);

			for (String t : text) {
				if (sb.length() > 0) {
					sb.append(StringPool.COLON);
				}

				sb.append(t);
			}

			String s = sb.toString();

			messageDigest.update(s.getBytes(Digester.ENCODING));
		} catch (NoSuchAlgorithmException nsae) {
			_log.error(nsae, nsae);
		} catch (UnsupportedEncodingException uee) {
			_log.error(uee, uee);
		}

		return messageDigest.digest();
	}

}