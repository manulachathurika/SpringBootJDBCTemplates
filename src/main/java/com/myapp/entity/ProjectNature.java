package com.myapp.entity;

public class ProjectNature {

	private int requestId;
	
	private String inputSet;
	
	private int inputSetSize;
	
	private String expectedOutput;
	
	private String executionFrequency;
	
	private String executionDateTime;
	
	private String deliveryFrequency;
	
	private String deliveryDateTime;
	
	private String deliveryReceipients;
	
	private String instructions;

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public String getInputSet() {
		return inputSet;
	}

	public void setInputSet(String inputSet) {
		this.inputSet = inputSet;
	}

	public int getInputSetSize() {
		return inputSetSize;
	}

	public void setInputSetSize(int inputSetSize) {
		this.inputSetSize = inputSetSize;
	}

	public String getExpectedOutput() {
		return expectedOutput;
	}

	public void setExpectedOutput(String expectedOutput) {
		this.expectedOutput = expectedOutput;
	}

	public String getExecutionFrequency() {
		return executionFrequency;
	}

	public void setExecutionFrequency(String executionFrequency) {
		this.executionFrequency = executionFrequency;
	}

	public String getExecutionDateTime() {
		return executionDateTime;
	}

	public void setExecutionDateTime(String executionDateTime) {
		this.executionDateTime = executionDateTime;
	}

	public String getDeliveryFrequency() {
		return deliveryFrequency;
	}

	public void setDeliveryFrequency(String deliveryFrequency) {
		this.deliveryFrequency = deliveryFrequency;
	}

	public String getDeliveryDateTime() {
		return deliveryDateTime;
	}

	public void setDeliveryDateTime(String deliveryDateTime) {
		this.deliveryDateTime = deliveryDateTime;
	}

	public String getDeliveryReceipients() {
		return deliveryReceipients;
	}

	public void setDeliveryReceipients(String deliveryReceipients) {
		this.deliveryReceipients = deliveryReceipients;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	@Override
	public String toString() {
		return "ProjectNature [requestId=" + requestId + ", inputSet=" + inputSet + ", inputSetSize=" + inputSetSize
				+ ", expectedOutput=" + expectedOutput + ", executionFrequency=" + executionFrequency
				+ ", executionDateTime=" + executionDateTime + ", deliveryFrequency=" + deliveryFrequency
				+ ", deliveryDateTime=" + deliveryDateTime + ", deliveryReceipients=" + deliveryReceipients
				+ ", instructions=" + instructions + "]";
	}
	
}
