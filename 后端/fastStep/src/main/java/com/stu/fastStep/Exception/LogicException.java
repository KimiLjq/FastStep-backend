package com.stu.fastStep.Exception;

public class LogicException extends Exception{
	static final long serialVersionUID = 1L;
	int errCode=400;
	String info="δ�����߼�������Ϣ";
	public LogicException(int errCode,String info) {
		this.errCode=errCode;
		this.info=info;
	}
	
	public void printStackTrace() {
		System.out.println("������룺"+errCode+"  ������Ϣ��"+info);
		super.printStackTrace();
	}
	
	
	public int getErrCode() {
		return errCode;
	}

	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}
