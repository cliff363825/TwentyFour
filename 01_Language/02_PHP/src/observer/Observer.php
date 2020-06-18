<?php

/**
 * Interface Observer
 * 抽象观察者：为所有的观察者定义一个接口
 */
interface Observer
{
    /**
     * 传入主题对象，得到主题对象的通知时更新自己
     * @param Subject $subject
     */
    public function update(Subject $subject);
}