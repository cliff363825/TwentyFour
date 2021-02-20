# ElasticSearch中"distinct","count"和"group by"的实现

来源：https://www.jianshu.com/p/62bed9cc8349

> 最近在业务中需要使用ES来进行数据查询，在某些场景下需要对数据进行去重，以及去重后的统计。为了方便大家理解，特意从SQL角度，方便大家能够理解ES查询语句。

## 1 - distinct

```
SELECT DISTINCT(user_id) FROM table WHERE user_id_type = 3;
```

```
{
  "query": {
    "term": {
      "user_id_type": 3
    }
  },
  "collapse": {
    "field": "user_id"
  }
}
```

```
{
  ...
  "hits": {
    "hits": [
      {
        "_index": "es_qd_mkt_visitor_packet_dev_v1_20180621",
        "_type": "ad_crowd",
        "_source": {
          "user_id": "wx2af8414b502d4ca2_oHtrD0Vxv-_8c678figJNHmtaVQQ",
          "user_id_type": 3
        },
        "fields": {
          "user_id": [
            "wx2af8414b502d4ca2_oHtrD0Vxv-_8c678figJNHmtaVQQ"
          ]
        }
      }
    ]
  }
}
```

总结：使用*collapse*字段后，查询结果中[hits]中会出现[fields]字段，其中包含了去重后的*user_id*

## 2 - count + distinct

```
SELECT COUNT(DISTINCT(user_id)) FROM table WHERE user_id_type = 3;
```

```
{
  "query": {
    "term": {
      "user_id_type": 3
    }
  },
  "aggs": {
    "count": {
      "cardinality": {
        "field": "user_id"
      }
    }
  }
}
```

```
{
  ...
  "hits": {
  ...
  },
  "aggregations": {
    "count": {
      "value": 121
    }
  }
}
```

总结：*aggs*中*cardinality*的字段代表需要*distinct*的字段

## 3 - count + group by

```
SELECT COUNT(user_id) FROM table GROUP BY user_id_type;
```

```
{
  "aggs": {
    "user_type": {
      "terms": {
        "field": "user_id_type"
      }
    }
  }
}
```

```
{
  ...
  "hits": {
    ...
  },
  "aggregations": {
    "user_type": {
      ...
      "buckets": [
        {
          "key": 4,
          "doc_count": 1220
        },
        {
          "key": 3,
          "doc_count": 488
        }
      ]
    }
  }
}
```

总结：*aggs*中*terms*的字段代表需要*gruop by*的字段

## 4 - count + distinct + group by

```
SELECT COUNT(DISTINCT(user_id)) FROM table GROUP BY user_id_type;
```

```
{
  "aggs": {
    "user_type": {
      "terms": {
        "field": "user_id_type"
      },
      "aggs": {
        "count": {
          "cardinality": {
            "field": "user_id"
          }
        }
      }
    }
  }
}
```

```
{
  ...
  "hits": {
    ...
  },
  "aggregations": {
    "user_type": {
      ...
      "buckets": [
        {
          "key": 4,
          "doc_count": 1220, //去重前数据1220条
          "count": {
            "value": 276 //去重后数据276条
          }
        },
        {
          "key": 3,
          "doc_count": 488, //去重前数据488条
          "count": {
            "value": 121 //去重后数据121条
          }
        }
      ]
    }
  }
}
```

## 5 - count + distinct + group by + where

```
SELECT COUNT(DISTINCT(user_id)) FROM table WHERE user_id_type = 2 GROUP BY user_id;
```

总结：对于既有*group by*又有*distinct*的查询要求，需要在*aggs*中嵌套子*aggs*

## 6 - 注意事项

**collapse关键字**

1. 折叠功能ES5.3版本之后才发布的。
2. 聚合&折叠只能针对keyword类型有效；

