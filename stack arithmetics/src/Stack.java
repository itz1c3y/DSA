
    public class Stack<T> {
        private static final int INITIAL_CAPACITY = 10;
        private T[] elements;
        private int size;
        @SuppressWarnings("unchecked")
        public Stack() {
            elements = (T[]) new Object[INITIAL_CAPACITY];
            size = 0;
        }

        public void printStack() {
            System.out.print("Stack elements: ");
            for (int i = 0; i < size; i++) {
                System.out.print(elements[i] + " ");
            }
            System.out.println();
        }

        public void push(T element) {
            if (size == elements.length) {
                resize();
            }
            elements[size++] = element;
        }

        public T pop() {
            if (isEmpty()) {
                throw new IllegalStateException("Stack is empty!");
            }
            T element = elements[--size];
            elements[size] = null; // Clear the reference
            return element;
        }

        public T peek() {
            if (isEmpty()) {
                throw new IllegalStateException("Stack is empty!");
            }
            return elements[size - 1];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }
        @SuppressWarnings("unchecked")
        private void resize() {
            T[] newElements = (T[]) new Object[elements.length * 2];
            System.arraycopy(elements, 0, newElements, 0, elements.length);
            elements = newElements;
        }

        public void clear() {
            for (int i = 0; i < size; i++) {
                elements[i] = null;
            }
            size = 0;
        }

    }


