package com.xy.utils;

import java.io.File;

/**
 * Excel文件过滤器
 */
import javax.swing.filechooser.FileFilter;
/**
 * Excle 文件过滤器
 * @author Lenovo
 *
 */
public class ExcleFileFilter extends FileFilter{

	@Override
	public boolean accept(File f) {
		String fname = f.getName().toLowerCase();
		return fname.endsWith(".xlsx")
				||fname.endsWith(".xls")
				||f.isDirectory();
	}

	@Override
	public String getDescription() {
		return "*.xlsx,*.xls";
	}

}
