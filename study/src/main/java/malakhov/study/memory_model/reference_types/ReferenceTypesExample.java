package malakhov.study.memory_model.reference_types;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class ReferenceTypesExample {
    public static void main(String[] args) throws InterruptedException {
        Object object = new Object();

        SoftReference<Object> softReference = new SoftReference<>(object);

        WeakReference<Object> weakReference = new WeakReference<>(object);

        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference<>(object, referenceQueue);

        object = null;

        System.out.println("\nTry to call GC");
        System.gc();
        System.out.println("After GC called\n");

        System.out.println("Strong reference:" + object);
        System.out.println("Soft reference: " + softReference.get());
        System.out.println("Weak reference:" + weakReference.get());
        System.out.println("Phanton reference:" + phantomReference.get());

        System.out.println("Reference queue poll: " + referenceQueue.poll());
    }
}
