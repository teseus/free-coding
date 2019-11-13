import io.reactivex.Observable;
import io.reactivex.Single;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class RxJavaCollectionOperators {
    @Test
    public void ToListOperator(){
        Single<List<String>> listSingle = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .toList();
        listSingle.subscribe(s-> System.out.println("Received 1 :" + s));

        Single<CopyOnWriteArrayList<String>> copyOnWriteArrayListSingle = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .toList(CopyOnWriteArrayList::new);

        copyOnWriteArrayListSingle.subscribe(s-> System.out.println("Received 2 :" + s));

        Observable.range(1, 10)
                .toList()
                .subscribe(s-> System.out.println("Received 3 :" + s));


        Observable.just(6, 2, 5, 7, 1, 4, 9, 8, 3)
                .toSortedList()
                .subscribe(s-> System.out.println("Received 4 :" + s));
    }

    @Test
    public void ToMap_Operator(){
        Single<Map<Character, String>> mapSingle = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .toMap(s -> s.charAt(0));
        mapSingle.subscribe(s-> System.out.println("Received 1 :" + s));

        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .toMap(s->s, v->v.length())
                .subscribe(s-> System.out.println("Received 2 :" + s));

        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .toMap(s->s.charAt(0), v->v.length(), () -> new ConcurrentHashMap<Character, Integer>())
                .subscribe(s-> System.out.println("Received 3 :" + s));

        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .toMap(s->s.length())
                .subscribe(s-> System.out.println("Received 4 :" + s));

        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .toMultimap(s->s.length())
                .subscribe(s-> System.out.println("Received :" + s));


    }
}
