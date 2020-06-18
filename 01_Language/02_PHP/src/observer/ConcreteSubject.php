<?php

/**
 * Class ConcreteSubject
 * 具体的主题对象
 */
class ConcreteSubject extends Subject
{
    /**
     * @var int 默认状态为0
     */
    private $state = 0;

    /**
     * @return int
     */
    public function getState()
    {
        return $this->state;
    }

    /**
     * @param int $state
     */
    public function setState($state)
    {
        $this->state = $state;
        // 当修改了主题对象状态时，通知所有观察者
        $this->notifyAllObserver();
    }
}