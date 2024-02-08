package tree;

import java.util.ArrayList;

import estrut.Tree;

public class BinarySearchTree implements Tree{
    private Node root;

    private class Node {
        int valor;
        Node left, right;

        public Node(int valor) {
            this.valor = valor;
        }
    }

    @Override
    public boolean buscaElemento(int valor) {
        return buscaElemento(root, valor);
    }

    private boolean buscaElemento(Node node, int valor) {
        if (node == null) {
            return false;
        }
        if (valor < node.valor) {
            return buscaElemento(node.left, valor);
        } else if (valor > node.valor) {
            return buscaElemento(node.right, valor);
        } else {
            return true;
        }
    }

    @Override
    public int minimo() {
        return minimo(root).valor;
    }

    private Node minimo(Node node) {
        if (node.left == null) {
            return node;
        } else {
            return minimo(node.left);
        }
    }

    @Override
    public int maximo() {
        return maximo(root).valor;
    }

    private Node maximo(Node node) {
        if (node.right == null) {
            return node;
        } else {
            return maximo(node.right);
        }
    }

    @Override
    public void insereElemento(int valor) {
        root = insereElemento(root, valor);
    }

    private Node insereElemento(Node node, int valor) {
        if (node == null) {
            return new Node(valor);
        }
        if (valor < node.valor) {
            node.left = insereElemento(node.left, valor);
        } else if (valor > node.valor) {
            node.right = insereElemento(node.right, valor);
        }
        return node;
    }

    @Override
    public void remove(int valor) {
        root = remove(root, valor);
    }

    private Node remove(Node node, int valor) {
        if (node == null) {
            return null;
        }
        if (valor < node.valor) {
            node.left = remove(node.left, valor);
        } else if (valor > node.valor) {
            node.right = remove(node.right, valor);
        } else {
            if (node.right == null) {
                return node.left;
            }
            if (node.left == null) {
                return node.right;
            }
            Node t = node;
            node = minimo(t.right);
            node.right = removeMin(t.right);
            node.left = t.left;
        }
        return node;
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = removeMin(node.left);
        return node;
    }

    @Override
    public int[] preOrdem() {
        ArrayList<Integer> res = new ArrayList<>();
        preOrdem(root, res);
        return res.stream().mapToInt(i->i).toArray();
    }

    private void preOrdem(Node node, ArrayList<Integer> res) {
        if (node == null) {
            return;
        }
        res.add(node.valor);
        preOrdem(node.left, res);
        preOrdem(node.right, res);
    }

    @Override
    public int[] emOrdem() {
        ArrayList<Integer> res = new ArrayList<>();
        emOrdem(root, res);
        return res.stream().mapToInt(i->i).toArray();
    }

    private void emOrdem(Node node, ArrayList<Integer> res) {
        if (node == null) {
            return;
        }
        emOrdem(node.left, res);
        res.add(node.valor);
        emOrdem(node.right, res);
    }

    @Override
    public int[] posOrdem() {
        ArrayList<Integer> res = new ArrayList<>();
        posOrdem(root, res);
        return res.stream().mapToInt(i->i).toArray();
    }

    private void posOrdem(Node node, ArrayList<Integer> res) {
        if (node == null) {
            return;
        }
        posOrdem(node.left, res);
        posOrdem(node.right, res);
        res.add(node.valor);
    }
}