<?php

/**
 * Class Subject
 * 抽象主题对象：把所有观察者对象都保存到一个集合里，每个主题都可以任何数量的观察者
 * 抽象主题对象提供了增加和删除观察者对象的方法
 */
class Subject
{
    /**
     * @var Observer[] 保存该主题下所有的观察者
     */
    protected $observers = [];

    /**
     * 添加观察者
     * @param Observer $observer
     */
    public function registerObserver(Observer $observer)
    {
        $this->observers[spl_object_hash($observer)] = $observer;
    }

    /**
     * 删除观察者
     * @param Observer $observer
     */
    public function removeObserver(Observer $observer)
    {
        unset($this->observers[spl_object_hash($observer)]);
    }

    /**
     * 通知所有观察者
     */
    public function notifyAllObserver()
    {
        foreach ($this->observers as $observer) {
            $observer->update($this); // 更新当前主题（subject）对象的信息到所有观察者中
        }
    }
}