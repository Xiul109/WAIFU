package waifu.agents;

import java.io.Serializable;

class Buffer{
	Serializable object=null;
	public Serializable getObject(){
		Serializable aux=object;
		object=null;
		return aux;
	}
	
	public void setObject(Serializable object){
		this.object=object;
	}

}
