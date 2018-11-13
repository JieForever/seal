package com.xy.utils.cae;


public interface CompressAndExtractFactory {
	
	/**
	 * ��ѹ�ļ�
	 * @param sourcePath Դѹ���ļ�·��
	 * @param targetPath ��ѹ���ļ����·��
	 * @param password ѹ���ļ�������
	 */
	public boolean doExtract(String sourcePath, String targetPath, String password);
	
	/**
	 * ѹ���ļ�
	 * @param comTargetFolder Ҫѹ�����ļ��е�·��
	 * @param resultFilePath ѹ���������·��,�����ļ���+��׺
	 * @return
	 */
	public boolean doCompress(String comTargetFolder, String resultFilePath);
}
