package com.xy.utils;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * Ñ¹ËõÎÄ¼ş¹ıÂËÆ÷
 * @author Lenovo
 *
 */
public class CompressedFileFilter extends FileFilter{
	
	@Override
	public boolean accept(File f) {
		String fname = f.getName().toLowerCase();
		return fname.endsWith(".zip")
				||fname.endsWith(".rar")
				||f.isDirectory();
	}

	@Override
	public String getDescription() {
		return "*.zip,*.rar";
	}

}
