/**
 * $Id: mxImageBundle.java,v 1.3 2010-11-13 08:46:10 gaudenz Exp $
 * Copyright (c) 2010, Gaudenz Alder
 */
package com.mxgraph.util;

import java.util.Hashtable;
import java.util.Map;

/**
 * Maps from keys to base64 encoded images or file locations. All values must
 * be URLs or use the format image/format:data, eg. image/gif:..., where data
 * is the base64 encoded binary data of the image (without the base64, prefix).
 * 
 * To add a new image bundle to an existing graph, the following code is used:
 * 
 * <code>
 * mxImageBundle bundle = new mxImageBundle();
 * bundle.putImage("myImage", "image/gif:R0lGODlhEAAQAMIGAAAAAICAAICAgP//AOz" +
 *   "p2O3r2////////yH+FUNyZWF0ZWQgd2l0aCBUaGUgR0lNUAAh+QQBCgAHACwAAAAAEAAQA" +
 *   "AADTXi63AowynnAMDfjPUDlnAAJhmeBFxAEloliKltWmiYCQvfVr6lBPB1ggxN1hilaSSA" +
 *   "SFQpIV5HJBDyHpqK2ejVRm2AAgZCdmCGO9CIBADs=");
 * graph.addImageBundle(bundle);
 * </code>
 * 
 * The image can then be referenced in any cell style using image=myImage.
 * If you are using mxOutline, you should use the same image bundles in the
 * graph that renders the outline.
 * 
 * To convert a given BufferedImage to a base64 encoded String, the following
 * code can be used:
 * 
 * <code>
 * ByteArrayOutputStream bos = new ByteArrayOutputStream();
 * ImageIO.write(image, "png", bos);
 * System.out.println("base64=" + mxBase64.encodeToString(
 * 	 bos.toByteArray(), false));
 * </code>
 * 
 * The value is decoded in mxUtils.loadImage. The keys for images are resolved
 * and the short format above is converted to a data URI in
 * mxGraph.postProcessCellStyle.
 */
public class mxImageBundle
{

	/**
	 * Maps from keys to images.
	 */
	protected Map<String, String> images = new Hashtable<String, String>();

	/**
	 * Returns the images.
	 */
	public Map<String, String> getImages()
	{
		return images;
	}

	/**
	 * Adds the specified entry to the map.
	 */
	public void putImage(String key, String value)
	{
		images.put(key, value);
	}

	/**
	 * Returns the value for the given key.
	 */
	public String getImage(String key)
	{
		return images.get(key);
	}

}
