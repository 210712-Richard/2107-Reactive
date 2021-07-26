package com.revature.reactive;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ReactiveJava {
	public static void main(String[] args) throws InterruptedException {
		//observable();
		schedulePeackock();
	}

	private static void schedulePeackock() throws InterruptedException {

		String[] arr = { "Peacock", "George", "Thumbtack", "Quentin", "Joshua", "Susan" };
		Observable<String> observable = Observable.fromArray(arr);
		observable.subscribeOn(Schedulers.newThread());
		observable.observeOn(Schedulers.newThread()).subscribe(new MyObserver("First", 1000));

		observable.observeOn(Schedulers.newThread()).subscribe(new MyObserver("second", 2000));
		while(true) {
			System.out.println("Waiting for processing to finish.");
			Thread.sleep(1000);
		}
	}

	private static void observable() {
		String[] arr = { "Peacock", "George", "Thumbtack", "Quentin", "Joshua", "Susan" };
		Observable<String> observable = Observable.fromArray(arr);
		observable.subscribe((s)->{
			System.out.println("first subsribe:" + s);
		});
		observable.subscribe((s)->{
			System.out.println("second subsribe:" + s);
		});
		
		Observer<String> o1 = new MyObserver("One");
		Observer<String> o2 = new MyObserver("Two");
		observable.subscribe(o1);
		observable.subscribe(o2);
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
