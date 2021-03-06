/*
 * Copyright (C) 2012 Markus Junginger, greenrobot (http://greenrobot.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tandong.sademo.eventbus;

import com.tandong.sa.eventbus.SubscriberExceptionEvent;

/**
 * @author Markus Junginger, greenrobot
 */
public class EventBusSubscriberExceptionTest extends AbstractEventBusTest {

    public void testSubscriberExceptionEvent() {
        eventBus.configureLogSubscriberExceptions(false);
        eventBus.register(this);
        eventBus.post("Foo");
        assertEventCount(1);
        assertEquals(SubscriberExceptionEvent.class, lastEvent.getClass());
        SubscriberExceptionEvent exEvent = (SubscriberExceptionEvent) lastEvent;
        assertEquals("Foo", exEvent.causingEvent);
        assertSame(this, exEvent.causingSubscriber);
        assertEquals("Bar", exEvent.throwable.getMessage());
    }

    public void testBadExceptionSubscriber() {
        eventBus.configureLogSubscriberExceptions(false);
        eventBus.register(this);
        eventBus.register(new BadExceptionSubscriber());
        eventBus.post("Foo");
        assertEventCount(1);
    }

    public void onEvent(String event) {
        throw new RuntimeException("Bar");
    }

    public void onEvent(SubscriberExceptionEvent event) {
        trackEvent(event);
    }

    class BadExceptionSubscriber {
        public void onEvent(SubscriberExceptionEvent event) {
            throw new RuntimeException("Bad");
        }
    }

}
