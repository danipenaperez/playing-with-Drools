package com.dppware.rulesKJarArtifact.bean.device;

public class Siren extends Device{
	
	public Siren(String id) {
		this.id=id;
	}
	public void trigger() {
		System.out.println("****************************");
		System.out.println("* ALARM!!! ALARM!! ALARM!! *");
		System.out.println("****************************");
	}
}
