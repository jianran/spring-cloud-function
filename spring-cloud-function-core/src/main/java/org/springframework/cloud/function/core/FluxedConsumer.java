/*
 * Copyright 2019-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.function.core;

import java.util.function.Consumer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Wrapper for a {@link Consumer} implementation that converts a reactive consumer into a
 * reactive function ({@code Function<Flux<?>, Mono<?>>}). This is primarily done for
 * consistent representation of reactive and non-reactive consumers.
 *
 * @param <I> input type of target consumer
 * @author Oleg Zhurakousky
 * @since 2.0.1
 * @see FluxConsumer
 *
 */
public class FluxedConsumer<I>
		extends WrappedFunction<I, Void, Flux<I>, Mono<Void>, Consumer<Flux<I>>> {

	public FluxedConsumer(Consumer<Flux<I>> target) {
		super(target);
	}

	@Override
	public Mono<Void> apply(Flux<I> input) {
		return Mono.fromRunnable(() -> this.getTarget().accept(input));
	}

}
