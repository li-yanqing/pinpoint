/*
 * Copyright 2017 NAVER Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.navercorp.pinpoint.profiler.context.provider.stat.activethread;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.navercorp.pinpoint.profiler.context.active.ActiveTraceHistogramFactory;
import com.navercorp.pinpoint.profiler.context.active.ActiveTraceLocator;
import com.navercorp.pinpoint.profiler.context.active.ActiveTraceRepository;
import com.navercorp.pinpoint.profiler.context.module.Nullable;
import com.navercorp.pinpoint.profiler.monitor.metric.activethread.ActiveTraceMetric;
import com.navercorp.pinpoint.profiler.monitor.metric.activethread.DefaultActiveTraceMetric;

/**
 * @author HyunGil Jeong
 */
public class ActiveTraceMetricProvider implements Provider<ActiveTraceMetric> {

    private final ActiveTraceLocator activeTraceLocator;

    @Inject
    public ActiveTraceMetricProvider(@Nullable /*TODO Disallow null*/ ActiveTraceRepository activeTraceLocator) {
        this.activeTraceLocator = activeTraceLocator;
    }

    @Override
    public ActiveTraceMetric get() {
        if (activeTraceLocator == null) {
            return ActiveTraceMetric.UNSUPPORTED_ACTIVE_TRACE_METRIC;
        } else {
            ActiveTraceHistogramFactory activeTraceHistogramFactory = new ActiveTraceHistogramFactory(activeTraceLocator);
            return new DefaultActiveTraceMetric(activeTraceHistogramFactory);
        }
    }
}
