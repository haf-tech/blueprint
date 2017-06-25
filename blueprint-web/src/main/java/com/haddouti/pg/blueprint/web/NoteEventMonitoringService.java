package com.haddouti.pg.blueprint.web;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codahale.metrics.Counter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import com.codahale.metrics.Timer.Context;
import com.haddouti.pg.blueprint.note.infra.monitoring.NoteEventMonitoring;

/**
 * Metric implementation using {@link MetricRegistry}.
 *
 */
@Service
public class NoteEventMonitoringService implements NoteEventMonitoring<Timer.Context> {

	@Autowired
	MetricRegistry metrics;

	private Timer responses;
	private Counter counter;

	@PostConstruct
	public void postConstruct() {

		responses = metrics.timer(MetricRegistry.name(NoteEventMonitoring.class, "responses"));
		counter = metrics.counter(MetricRegistry.name(NoteEventMonitoring.class, "executed-events"));
	}

	@Override
	public void incr() {
		counter.inc();
	}

	@Override
	public Context startTimer() {

		return responses.time();
	}

	@Override
	public void stopTimer(Context t) {

		t.stop();
	}

}
