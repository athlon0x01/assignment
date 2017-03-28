package my.luxoft.assignment.first;

/**
 * We have a class representing binary tree nodes:
 * class BTN {
 * int val;
 * BTN left;
 * BTN right;
 * }
 * please implement Java method to compare 2 such trees (returns: true if fully equal, false - otherwise)
 *
 * @author Anton Borovyk
 */
public class BTN implements Comparable<BTN>{
    int val;
    BTN left;
    BTN right;

    public BTN(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }

    public BTN(int val, BTN left, BTN right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(BTN o) {
        if (o == null) {
            return -1;
        }
        if (val != o.val) {
            return val - o.val;
        }
        int le = (left != null) ? left.compareTo(o.left) : o.left == null ? 0 : 1;
        int re = (right != null) ? right.compareTo(o.right) : o.right == null ? 0 : -1;

        return (le == 0 || re == 0) ? re + le : re * le;
    }
}
