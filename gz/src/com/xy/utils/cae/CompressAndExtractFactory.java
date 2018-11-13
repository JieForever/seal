package com.xy.utils.cae;


public interface CompressAndExtractFactory {
	
	/**
	 * 解压文件
	 * @param sourcePath 源压缩文件路径
	 * @param targetPath 解压后文件存放路径
	 * @param password 压缩文件的密码
	 */
	public boolean doExtract(String sourcePath, String targetPath, String password);
	
	/**
	 * 压缩文件
	 * @param comTargetFolder 要压缩的文件夹的路径
	 * @param resultFilePath 压缩后结果存放路径,包含文件名+后缀
	 * @return
	 */
	public boolean doCompress(String comTargetFolder, String resultFilePath);
}
