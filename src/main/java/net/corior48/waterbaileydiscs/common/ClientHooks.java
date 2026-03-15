package net.corior48.waterbaileydiscs.common;

import net.corior48.waterbaileydiscs.client.handler.ClientPayloadHandler;

public class ClientHooks {
    public static ClientPayloadHandler.ClientAccess ACCESS = payload -> {
        // no-op on dedicated server
    };
}