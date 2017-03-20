package widget;


public interface IViewHolder<T> {

    void setViewEventListener(ViewEventListener<T> viewEventListener);

    void setItem(T item);

    void setPosition(int position);
}
