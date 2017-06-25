package com.haddouti.pg.blueprint.note.infra.monitoring;

/**
 * An interface for metric implementation for note events.
 *
 * @param <T>
 */
public interface NoteEventMonitoring<T> {

	public void incr();

	public T startTimer();

	public void stopTimer(T t);
}
