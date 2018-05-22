/**
 * 文件名为UserQuery.thrift
 * 实现功能：创建一个查询请求struct，查询结果struct，和一个服务接口service
 * 基于：thrift-0.11.0
 **/
namespace java com.vanseed.mimas.rpc.thrift.user
struct QryRequest {
        /**
         * 用户id
         */
        1:i32 userId;
        /**
         * 用户名
         */
        2:string userName;
}
struct QryResult {
        /**
         *返回码, 1成功，0失败
         */
        1:i32 code;
        /**
         *响应信息
         */
        2:string userName;
	 	/**
         *响应信息
         */
        3:string userMobile;
}
service UserQuery{
        /**
         * 测试查询接口，当qryCode值为1时返回"成功"的响应信息，qryCode值为其他值时返回"失败"的响应信息
         * @param QryRequest
         */
        QryResult qryUser(QryRequest request);
}