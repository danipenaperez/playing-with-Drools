package com.dppware.rulesKJarArtifact.bean.device;

public class DoorLock extends Device{

	//private static final Logger log = LoggerFactory.getLogger(Lock.class);
	
	public void close() {
		this.status="closed";
		//log.info("Cerrando la puerta...");
	}

	public void open() {
		this.status="open";
		//log.info("Abriendo la puerta...");
		
	}

	public DoorLock(String id) {
		this.id=id;
	}
	
	public DoorLock(String id, String initialStatus) {
		this.id=id;
		this.status=initialStatus;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean eq=false;
		if(obj instanceof DoorLock) {
			DoorLock _o =(DoorLock)obj;
			if(_o.getId().equals(this.id)) {
				eq=true;
			}
		}
		return eq;
	}
	
}
