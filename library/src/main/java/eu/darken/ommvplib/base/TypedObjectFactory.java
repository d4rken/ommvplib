package eu.darken.ommvplib.base;

public interface TypedObjectFactory<TypeT> {
    TypeT create();

    Class<? extends TypeT> getTypeClazz();
}
