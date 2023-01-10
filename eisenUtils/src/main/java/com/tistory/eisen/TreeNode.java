package com.tistory.eisen;

/**
 * packageName : com.tistory.eisen
 * fileName : TreeNode
 * author : eisen
 * date : 2023-01-10
 * description :
 * <p>
 * -----------------------------------------------------
 * DATE: 2023-01-10    AUTHOR: eisen    NOTE: 최초 생성
 */
public class TreeNode {
    public int val;
    public TreeNode right;
    public TreeNode left;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
