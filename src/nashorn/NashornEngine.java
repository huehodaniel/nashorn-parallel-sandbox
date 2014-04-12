package nashorn;

import java.io.Reader;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class NashornEngine implements ScriptEngine {
	
	private static final ScriptEngineManager manager = new ScriptEngineManager();
	private final ScriptEngine delegate;
	
	public NashornEngine() {
		delegate = manager.getEngineByName("nashorn");
	}

	public Object eval(String script, ScriptContext context)
			throws ScriptException {
		return delegate.eval(script, context);
	}

	public Object eval(Reader reader, ScriptContext context)
			throws ScriptException {
		return delegate.eval(reader, context);
	}

	public Object eval(String script) throws ScriptException {
		return delegate.eval(script);
	}

	public Object eval(Reader reader) throws ScriptException {
		return delegate.eval(reader);
	}

	public Object eval(String script, Bindings n) throws ScriptException {
		return delegate.eval(script, n);
	}

	public Object eval(Reader reader, Bindings n) throws ScriptException {
		return delegate.eval(reader, n);
	}

	public void put(String key, Object value) {
		delegate.put(key, value);
	}

	public Object get(String key) {
		return delegate.get(key);
	}

	public Bindings getBindings(int scope) {
		return delegate.getBindings(scope);
	}

	public void setBindings(Bindings bindings, int scope) {
		delegate.setBindings(bindings, scope);
	}

	public Bindings createBindings() {
		return delegate.createBindings();
	}

	public ScriptContext getContext() {
		return delegate.getContext();
	}

	public void setContext(ScriptContext context) {
		delegate.setContext(context);
	}

	public ScriptEngineFactory getFactory() {
		return delegate.getFactory();
	}

}
