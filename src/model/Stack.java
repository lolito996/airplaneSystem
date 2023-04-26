package  model;
    public class Stack<T> {

        private Node<T> top;

        public Stack() {
            top = null;
        }

        public boolean isEmpty() {
            return top == null;
        }

        public void push(T element,K key) {
            Node<T> newNode = new Node<>(element,key);
            newNode.setNext(top);
            top = newNode;
        }

        public T pop() throws RuntimeException {
            if (isEmpty()) {
                throw new RuntimeException("Stack is empty");
            } else {
                Node<T> node = top;
                top = top.getNext();
                return node.getElement();
            }
        }

        public T peek() throws RuntimeException {
            if (isEmpty()) {
                throw new RuntimeException("Stack is empty");
            } else {
                return top.getElement();
            }
        }
    }