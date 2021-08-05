package dev.vatuu.backbone.utils.math;

import com.google.common.collect.Queues;
import net.minecraft.util.Util;

import java.util.ArrayDeque;
import java.util.Deque;

public class MatrixStack {

    private final Deque<Matrix4> stack = Util.make(Queues.newArrayDeque(), q -> q.add(new Matrix4()));

    public MatrixStack push() {
        stack.addLast(new Matrix4(stack.getLast()));
        return this;
    }

    public MatrixStack pop() {
        stack.removeLast();
        return this;
    }

    public Matrix4 peek() {
        return stack.getLast();
    }

    public MatrixStack translate(float x, float y, float z) {
        peek().multiply(Matrix4.translate(x, y, z));
        return this;
    }

    public MatrixStack scale(float x, float y, float z) {
        peek().multiply(Matrix4.scale(x, y, z));
        return this;
    }

    public MatrixStack rotate(Quaternion rot) {
        peek().multiply(rot);
        return this;
    }

    public MatrixStack multiply(Matrix4 mat) {
        peek().multiply(mat);
        return this;
    }

    public boolean isEmpty() {
        return stack.size() == 1;
    }
}
