package eu.darken.ommvplib.base;

public interface ObjectFactory<TypeT> {
    TypeT create();

    Class<? extends TypeT> getTypeClazz();
}
