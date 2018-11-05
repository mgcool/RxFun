package nstv.rxfun.data;

import io.reactivex.Scheduler;

public interface SchedulersProvider {

    Scheduler mainScheduler();
    Scheduler backgroundScheduler();

}
