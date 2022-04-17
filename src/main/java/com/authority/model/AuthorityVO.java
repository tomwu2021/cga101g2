package com.authority.model;

public class AuthorityVO implements java.io.Serializable {
	private Integer functionId;
	private String functionName;

	public AuthorityVO() {
	}

	public Integer getFunctionId() {
		return functionId;
	}

	public void setFunctionId(Integer functionId) {
		this.functionId = functionId;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

}
