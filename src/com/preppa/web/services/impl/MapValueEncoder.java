package com.preppa.web.services.impl;

/*
 * Preppa, Inc.
 * 
 * Copyright 2009. All rights reserved.
 * 
 * $Id$
 */


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.log4j.Logger;
import org.apache.tapestry5.ValueEncoder;
/**
 *
 * @author newtonik
 */
public class MapValueEncoder implements ValueEncoder {

        static final Logger log = Logger.getLogger(MapValueEncoder.class);

        private static final String DELIM = "_";

        @Override
        public String toClient(Object value) {
                String res = "";
                Iterator<String> it = null;
                Map<String,Object> map = (Map<String, Object>) value;

                if (value instanceof ListOrderedMap) {
                        ListOrderedMap lomap = (ListOrderedMap) value;
                        it = lomap.keySet().iterator();

                } else {
                        it = map.keySet().iterator();

                }

                while(it.hasNext()) {
                        String key = it.next();
                        Object val = map.get(key);
                        if (val != null) {
                                if (res.length() > 0) {
                                        res += DELIM;
                                }
                                res += key + DELIM + escapeString(val.toString());
                        }
                }


                return res;
        }

        @Override
        public Object toValue(String clientValue) {
                String[] tokens = clientValue.split(DELIM);
                ListOrderedMap res = new ListOrderedMap();
                boolean tokIsKey = true;
                String currKey = null;
                for (int i = 0; i < tokens.length; i++) {
                        String tok = tokens[i];
                        if (tokIsKey) {
                                currKey = tok;

                        } else {
                                res.put(currKey, descapeString(tok));

                        }

                        tokIsKey = !tokIsKey;
                }

                return res;
        }

        public static String escapeString(String string) {
                try {
                        return URLEncoder.encode(string, "UTF-8");

                } catch (UnsupportedEncodingException e) {
                        log.error("unable to encode : " + string, e);
                        return "";
                }
        }

        public static String descapeString(String string) {
                try {
                        return URLDecoder.decode(string, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                        log.error("unable to descape : " + string, e);
                        return "";
                }
        }

}

