# [Alter table without locking the entire table](https://stackoverflow.com/questions/35424543/alter-table-without-locking-the-entire-table)

Does

```sql
ALTER TABLE sample ADD COLUMN `hasItem` tinyint(1) DEFAULT NULL
```

lock the entire table?



Short answer: For MySQL < 5.6 locks are required. From 5.6 on, and using InnoDB, [locks are not required for many `ALTER TABLE` operations including adding a column](https://dev.mysql.com/doc/refman/5.6/en/innodb-online-ddl-operations.html).

------

If you're using MySQL 5.5 or older, it will get a read lock for the whole operation and then a brief write lock at the end.

[From the MySQL documentation for ALTER TABLE](https://dev.mysql.com/doc/refman/5.5/en/alter-table.html)...

> In most cases, ALTER TABLE makes a temporary copy of the original table... While ALTER TABLE is executing, ***the original table is readable by other sessions*** (with the exception noted shortly). Updates and writes to the table that begin after the ALTER TABLE operation begins are stalled until the new table is ready...
>
> The exception referred to earlier is that ALTER TABLE blocks reads (not just writes) at the point where it is ready to install a new version of the table .frm file, discard the old file, and clear outdated table structures from the table and table definition caches. At this point, it must acquire an exclusive lock.

Which is to say, when adding a column it read locks the table for most of the operation, then gets a write lock at the end.

------

MySQL 5.6 added the [Online DDL](https://dev.mysql.com/doc/refman/5.6/en/innodb-online-ddl.html) to InnoDB which speeds up and improves many things such as altering tables and indexes. Adding a column to a table will no longer require table locks [except possibly brief exclusive locks at the start and end of the operation](https://dev.mysql.com/doc/refman/5.5/en/innodb-create-index-limitations.html).

It *should* happen automatically, but to be sure set `ALGORITHM=inplace` and `LOCK=none` to your `ALTER TABLE` statement.

There is one exception...

> InnoDB tables created before MySQL 5.6 do not support ALTER TABLE ... ALGORITHM=INPLACE for tables that include temporal columns (DATE, DATETIME or TIMESTAMP) and have not been rebuilt using ALTER TABLE ... ALGORITHM=COPY.