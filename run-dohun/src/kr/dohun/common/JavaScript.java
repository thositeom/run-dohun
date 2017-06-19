package kr.dohun.common;

import java.io.Reader;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class JavaScript implements ScriptEngine {
	
	@Override
	public Bindings createBindings() {
		ScriptEngineManager manager = new ScriptEngineManager();
        // Obtain a ScriptEngine that supports the JavaScript short name.
        ScriptEngine engine = manager.getEngineByName("JavaScript");
		
		return null;
	}

	@Override
	public Object eval(String arg0) throws ScriptException {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public Object eval(Reader arg0) throws ScriptException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object eval(String arg0, ScriptContext arg1) throws ScriptException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object eval(Reader arg0, ScriptContext arg1) throws ScriptException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object eval(String arg0, Bindings arg1) throws ScriptException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object eval(Reader arg0, Bindings arg1) throws ScriptException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object get(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bindings getBindings(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScriptContext getContext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScriptEngineFactory getFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void put(String arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBindings(Bindings arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setContext(ScriptContext arg0) {
		// TODO Auto-generated method stub
		
	}

}
