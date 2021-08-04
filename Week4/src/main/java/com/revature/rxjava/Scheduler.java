package com.revature.rxjava;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;

public class Scheduler {
	public static void main(String[] args) {
		//peacockFromLastWeek();
		//peacockWithNoScheduler();
		//peacockWithSingle();
		//peacockWithFrom();
		subjectExample();
	}

	private static void subjectExample() {
		String[] arr = { "Peacock", "George", "Thumbtack", "Quentin", "Joshua", "Susan" };
		Observable<String> observable = Observable.fromArray(arr);
		
		Subject subject = PublishSubject.create();
		
		subject.observeOn(Schedulers.newThread()).subscribe(new MyObserver("First", 100));

		subject.observeOn(Schedulers.newThread()).subscribe(new MyObserver("second", 200));

		observable.observeOn(Schedulers.newThread()).subscribe(subject);
		
		while(true) {
			System.out.println("Waiting for processing to finish.");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private static void peacockWithFrom() {
		String[] arr = { "Peacock", "George", "Thumbtack", "Quentin", "Joshua", "Susan" };
		Observable<String> observable = Observable.fromArray(arr);
		ExecutorService pool = Executors.newFixedThreadPool(2);
		observable.observeOn(Schedulers.from(pool)).subscribe(new MyObserver("First", 1000));

		observable.observeOn(Schedulers.from(pool)).subscribe(new MyObserver("second", 2000));
		while(true) {
			System.out.println("Waiting for processing to finish.");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private static void peacockWithSingle() {
		String[] arr = { "Peacock", "George", "Thumbtack", "Quentin", "Joshua", "Susan" };
		Observable<String> observable = Observable.fromArray(arr);
		observable.observeOn(Schedulers.single()).subscribe(new MyObserver("First", 1000));

		observable.observeOn(Schedulers.single()).subscribe(new MyObserver("second", 2000));
		while(true) {
			System.out.println("Waiting for processing to finish.");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private static void peacockWithNoScheduler() {
		String[] arr = { "Peacock", "George", "Thumbtack", "Quentin", "Joshua", "Susan" };
		Observable<String> observable = Observable.fromArray(arr);

		observable.subscribe(new MyObserver("First", 1000));

		observable.subscribe(new MyObserver("second", 2000));
		
	}

	private static void peacockFromLastWeek() {
		String[] arr = { "Peacock", "George", "Thumbtack", "Quentin", "Joshua", "Susan" };
		Observable<String> observable = Observable.fromArray(arr);
		observable.subscribeOn(Schedulers.newThread());
		observable.observeOn(Schedulers.newThread()).subscribe(new MyObserver("First", 1000));

		observable.observeOn(Schedulers.newThread()).subscribe(new MyObserver("second", 2000));
		while(true) {
			System.out.println("Waiting for processing to finish.");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class MyObserver implements Observer {
	private String name;
	private long time = 0;
	
	public MyObserver(String name) {
		this.name = name;
	}
	
	public MyObserver(String name, long time) {
		this(name);
		this.time = time;
	}
	@Override
	public void onSubscribe(Disposable d) {
		// this method gets called on the observer by the observable when it subscribes.
		System.out.println("Successful subsciption");
	}
	@Override
	public void onNext(Object t) {
		// this method is called with the next element in the sequence when that element becomes available.
		System.out.println("Observer "+name+" sees "+ t);
		try {
			Thread.sleep(time); // sleep for a given time in ms
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Observer "+name+ " is on thread: "+ Thread.currentThread().getName());
	}
	@Override
	public void onComplete() {
		// this method gets called when we are no longer receiving notifications. This effectively signals that the subscription has ended.
		System.out.println("Nothing else");
	}
	@Override
	public void onError(Throwable e) {
		// the method that gets called when an exception is thrown by the observable
		System.err.println(e);
	}
}

