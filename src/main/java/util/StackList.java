package util;

import java.util.Iterator;
import java.util.NoSuchElementException;

class DoublyLink<T> {
    public T Data;
    public DoublyLink<T> next;
    public DoublyLink<T> previous;

    public DoublyLink(T Data) {
        this.Data = Data;
    }

    public void displayLink() {
        System.out.print(Data + " ");
    }
}

public class StackList<T> implements Iterable<T> {
    private DoublyLink<T> first;
    private DoublyLink<T> last;

    public StackList() {
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void insertFirst(T Data) {
        DoublyLink<T> newLink = new DoublyLink<T>(Data);
        if (isEmpty()) {
            last = newLink;
        } else {
            first.previous = newLink;
        }
        newLink.next = first;
        first = newLink;
    }

    public void insertLast(T Data) {
        DoublyLink<T> newLink = new DoublyLink<T>(Data);
        if (isEmpty()) {
            first = newLink;
        } else {
            last.next = newLink;
            newLink.previous = last;
        }
        last = newLink;
    }

    public DoublyLink<T> deleteFirst() {
        DoublyLink<T> temp = first;
        if (first.next == null) {
            last = null;
        } else {
            first.next.previous = null;
        }
        first = first.next;
        return temp;
    }

    public DoublyLink<T> deleteLast() {
        DoublyLink<T> temp = last;
        if (first.next == null) {
            first = null;
        } else {
            last.previous.next = null;
        }
        last = last.previous;
        return temp;
    }

    public boolean insertAfter(T key, T Data) {
        DoublyLink<T> current = first;
        while (current != null && !current.Data.equals(key)) {
            current = current.next;
        }
        if (current == null) {
            return false;
        }
        DoublyLink<T> newLink = new DoublyLink<T>(Data);
        if (current == last) {
            newLink.next = null;
            last = newLink;
        } else {
            newLink.next = current.next;
            current.next.previous = newLink;
        }
        newLink.previous = current;
        current.next = newLink;
        return true;
    }

    public DoublyLink<T> deleteKey(T key) {
        DoublyLink<T> current = first;
        while (current != null && !current.Data.equals(key)) {
            current = current.next;
        }
        if (current == null) {
            return null;
        }
        if (current == first) {
            first = current.next;
        } else {
            current.previous.next = current.next;
        }
        if (current == last) {
            last = current.previous;
        } else {
            current.next.previous = current.previous;
        }
        return current;
    }

    public void displayForward() {
        System.out.print("List " + "(first-->last): ");
        DoublyLink<T> current = first;
        while (current != null) {
            current.displayLink();
            current = current.next;
        }
        System.out.println();
    }

    public void displayBackward() {
        System.out.print("List " + "(last-->first): ");
        DoublyLink<T> current = last;
        while (current != null) {
            current.displayLink();
            current = current.previous;
        }
        System.out.println();
    }

    @Override
    public Iterator<T> iterator() {
        return new StackIterator();
    }

    private class StackIterator implements Iterator<T> {
        private DoublyLink<T> current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T data = current.Data;
            current = current.next;
            return data;
        }
    }

}
