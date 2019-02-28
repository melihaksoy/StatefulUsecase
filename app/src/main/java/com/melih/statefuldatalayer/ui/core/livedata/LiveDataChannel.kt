package com.melih.statefuldatalayer.ui.core.livedata

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.atomic.AtomicInteger

class LiveDataChannel<T> : MutableLiveData<T>() {

    private var triggerCount = AtomicInteger(0)
    private var triggerChecker = AtomicInteger(0)

    private val internalQueue = ConcurrentLinkedQueue<T>()
    private var internalObserver: Observer<in T> = Observer {
        triggerCount.incrementAndGet()
        userObserver.onChanged(it)
    }

    private lateinit var userObserver: Observer<in T>

    override fun postValue(value: T) {
        internalQueue.offer(value)

        if (hasActiveObservers()) {
            if (triggerChecker.compareAndSet(triggerCount.get(), triggerCount.get() + 1)) {
                with(internalQueue) {
                    if (peek() != null) {
                        internalObserver.onChanged(poll())
                    }
                }
            }
        }
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        userObserver = observer
        super.observe(owner, internalObserver)
    }
}