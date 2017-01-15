package eu.darken.ommvplib.core;

interface TypedObjectFactory<TypeT> {
    TypeT create();

    Class<? extends TypeT> getTypeClazz();
}
