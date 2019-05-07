package com.abstractrestapplication.interfaces;

import java.io.Serializable;

public interface HateoasObject<Identifier extends Serializable> {
    Identifier getIdentifier();
}
