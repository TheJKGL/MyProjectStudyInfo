package malakhov.bobocod.PersisteceReflectionDemoApp.orm;

public interface Session {

    <T> T find(Class<T> type, Object id);
}
