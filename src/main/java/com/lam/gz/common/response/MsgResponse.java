package com.lam.gz.common.response;

import com.lam.gz.common.exception.BaseException;
import com.lam.gz.common.util.StringUtil;

/**
 * @author bo.liu01 on 2017/10/30.
 */
/**
 * @author liubo
 *
 */
public class MsgResponse extends ResultResponse {

	private static final long serialVersionUID = -8299744027840855873L;

	protected boolean rel = false;

    private static final String CC = ", ";

    public MsgResponse() {
        super();
    }

    public MsgResponse(BaseException e) {
        super(e);
        this.rel = false;
    }

    public MsgResponse(MsgCode msgResponse) {
        super(msgResponse);
        if (msgResponse == MsgCode.SUCCESS){
            rel(true);
        }
    }

    public ResultResponse addMsg(String msg) {
        setMsg(this.msg + CC + msg);
        return this;
    }

    public MsgResponse rel(boolean rel) {
        this.rel = rel;
        if (rel){
            this.code = MsgCode.SUCCESS.getCode();
            this.msg = StringUtil.isBlank(this.msg)? MsgCode.SUCCESS.getMsg() :this.msg;
        }else{
            this.code = code == 0 ? MsgCode.FAIL.getCode() : code;
            this.msg = StringUtil.isBlank(this.msg)? MsgCode.FAIL.getMsg() :this.msg;
        }
        return this;
    }

    public boolean isRel() {
        return rel;
    }

    public void setRel(boolean rel) {
        this.rel = rel;
    }
}
