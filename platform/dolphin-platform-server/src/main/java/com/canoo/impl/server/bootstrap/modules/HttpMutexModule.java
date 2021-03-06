package com.canoo.impl.server.bootstrap.modules;

import com.canoo.impl.platform.core.Assert;
import com.canoo.impl.server.servlet.HttpSessionMutexHolder;
import com.canoo.platform.server.spi.AbstractBaseModule;
import com.canoo.platform.server.spi.ModuleDefinition;
import com.canoo.platform.server.spi.ModuleInitializationException;
import com.canoo.platform.server.spi.ServerCoreComponents;

import javax.servlet.ServletContext;

@ModuleDefinition(HttpMutexModule.HTTP_MUTEX_MODULE)
public class HttpMutexModule extends AbstractBaseModule {

    public static final String HTTP_MUTEX_MODULE = "HttpMutexModule";

    public static final String HTTP_MUTEX_MODULE_ACTIVE = "httpMutexModuleActive";

    @Override
    protected String getActivePropertyName() {
        return HTTP_MUTEX_MODULE_ACTIVE;
    }

    @Override
    public void initialize(final ServerCoreComponents coreComponents) throws ModuleInitializationException {
        Assert.requireNonNull(coreComponents, "coreComponents");
        final ServletContext servletContext = coreComponents.getServletContext();
        Assert.requireNonNull(servletContext, "servletContext");

        final HttpSessionMutexHolder mutexHolder = new HttpSessionMutexHolder();
        servletContext.addListener(mutexHolder);
    }
}
