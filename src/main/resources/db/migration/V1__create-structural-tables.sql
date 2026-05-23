CREATE SEQUENCE RV_SEQ_USERS
    START WITH 1
    INCREMENT BY 1
    NOCACHE
  NOCYCLE;

CREATE TABLE RV_T_USERS (
                              user_oid INTEGER DEFAULT RV_SEQ_USERS.NEXTVAL NOT NULL,
                              name VARCHAR2(100) NOT NULL,
                              email VARCHAR(100) NOT NULL,
                              password VARCHAR(20) NOT NULL
);

CREATE TABLE RV_T_COLLECTION_POINT (
                                       id_point        NUMBER(10)      GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                                       name            VARCHAR2(100)   NOT NULL,
                                       address         VARCHAR2(200)   NOT NULL,
                                       city            VARCHAR2(100)   NOT NULL,
                                       capacity_kg     NUMBER(10,2)    NOT NULL,
                                       active          CHAR(1)         DEFAULT 'Y' NOT NULL CHECK (active IN ('Y','N'))
);

COMMENT ON TABLE  RV_T_COLLECTION_POINT             IS 'Physical selective waste collection points';
COMMENT ON COLUMN RV_T_COLLECTION_POINT.capacity_kg IS 'Maximum capacity of the collection point in kilograms';
COMMENT ON COLUMN RV_T_COLLECTION_POINT.active      IS 'Y = Active, N = Inactive';

-- ============================================================
-- TABLE: RV_T_CONTAINER
-- Containers for each waste type per collection point.
-- ============================================================

CREATE TABLE RV_T_CONTAINER (
                                id_container        NUMBER(10)      GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                                id_point            NUMBER(10)      NOT NULL,
                                waste_type          VARCHAR2(50)    NOT NULL
                            CHECK (waste_type IN ('PLASTIC','PAPER','METAL','GLASS','ORGANIC','ELECTRONIC')),
                                capacity_kg         NUMBER(10,2)    NOT NULL,
                                current_volume_kg   NUMBER(10,2)    DEFAULT 0 NOT NULL,
                                usage_percentage    NUMBER(5,2)     GENERATED ALWAYS AS (
                            ROUND((current_volume_kg / NULLIF(capacity_kg,0)) * 100, 2)
                        ) VIRTUAL,
                                CONSTRAINT fk_container_point FOREIGN KEY (id_point)
                                    REFERENCES RV_T_COLLECTION_POINT(id_point),
                                CONSTRAINT chk_current_volume CHECK (current_volume_kg >= 0)
);

COMMENT ON TABLE  RV_T_CONTAINER                  IS 'Containers for each waste type per collection point';
COMMENT ON COLUMN RV_T_CONTAINER.usage_percentage IS 'Occupancy percentage calculated automatically';


-- ============================================================
-- TABLE: RV_T_COLLECTION
-- History of collections performed on containers.
-- ============================================================

CREATE TABLE RV_T_COLLECTION (
                                 id_collection       NUMBER(10)      GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                                 id_container        NUMBER(10)      NOT NULL,
                                 collection_date     DATE            DEFAULT SYSDATE NOT NULL,
                                 collected_weight_kg NUMBER(10,2)    NOT NULL,
                                 responsible         VARCHAR2(100)   NOT NULL,
                                 destination         VARCHAR2(100)   NOT NULL,
                                 notes               VARCHAR2(300),
                                 CONSTRAINT fk_collection_container FOREIGN KEY (id_container)
                                     REFERENCES RV_T_CONTAINER(id_container)
);

COMMENT ON TABLE RV_T_COLLECTION IS 'History of collections performed on containers';


-- ============================================================
-- TABLE: RV_T_ALERT
-- Automatic alerts generated for containers in critical state.
-- ============================================================

CREATE TABLE RV_T_ALERT (
                            id_alert        NUMBER(10)      GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                            id_container    NUMBER(10)      NOT NULL,
                            alert_type      VARCHAR2(50)    NOT NULL
                        CHECK (alert_type IN ('CRITICAL_CAPACITY','PENDING_COLLECTION','OVERFLOW')),
                            message         VARCHAR2(500)   NOT NULL,
                            created_at      DATE            DEFAULT SYSDATE NOT NULL,
                            resolved        CHAR(1)         DEFAULT 'N' NOT NULL CHECK (resolved IN ('Y','N')),
                            resolved_at     DATE,
                            CONSTRAINT fk_alert_container FOREIGN KEY (id_container)
                                REFERENCES RV_T_CONTAINER(id_container)
);

COMMENT ON TABLE RV_T_ALERT IS 'Automatic alerts generated for containers in critical state';

-- ============================================================
-- TABLE: RV_T_USER_NOTIFICATION
-- Notifications sent to users about collection alerts.
-- ============================================================

CREATE TABLE RV_T_USER_NOTIFICATION (
                                        id_notification NUMBER(10)      GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                                        id_alert        NUMBER(10)      NOT NULL,
                                        recipient       VARCHAR2(150)   NOT NULL,
                                        channel         VARCHAR2(20)    NOT NULL CHECK (channel IN ('EMAIL','SMS','SYSTEM')),
                                        message         VARCHAR2(500)   NOT NULL,
                                        sent_at         DATE            DEFAULT SYSDATE NOT NULL,
                                        sent            CHAR(1)         DEFAULT 'N' NOT NULL CHECK (sent IN ('Y','N')),
                                        CONSTRAINT fk_notification_alert FOREIGN KEY (id_alert)
                                            REFERENCES RV_T_ALERT(id_alert)
);

COMMENT ON TABLE RV_T_USER_NOTIFICATION IS 'Notifications sent to users about collection alerts';

COMMIT;