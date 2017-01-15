package eu.darken.ommvplib.base;

interface ObjectFactory<TypeT> {
    TypeT create();

    Class<? extends TypeT> getTypeClazz();
}
