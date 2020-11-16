CREATE FUNCTION b2n(bool) RETURNS Bit AS
'select
  case
    when $1 IS TRUE
    then CAST(1 AS Bit)
    else CAST(0 AS Bit)
    end;'
LANGUAGE SQL;



create cast(bool as Bit) WITH FUNCTION b2n(bool) AS ASSIGNMENT;