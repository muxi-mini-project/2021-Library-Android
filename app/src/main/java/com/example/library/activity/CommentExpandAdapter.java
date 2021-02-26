package com.example.library.activity;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.library.R;
import com.example.library.data.CommentDetail;
import com.example.library.data.ReplyDetail;

import java.util.ArrayList;
import java.util.List;

public class CommentExpandAdapter extends BaseExpandableListAdapter {
    private static final String TAG = "CommentExpandAdapter";
    private final Context context;
    private final List<CommentDetail> mCommentDetailList;

    public CommentExpandAdapter(Context context, List<CommentDetail> commentDetailList) {
        this.context = context;
        this.mCommentDetailList = commentDetailList;
    }

    //返回group分组的数量，在当前需求中指代评论的数量
    @Override
    public int getGroupCount() {
        return mCommentDetailList.size();
    }

    //返回所在group中child的数量，这里指代当前评论对应的回复数目
    @Override
    public int getChildrenCount(int position) {
        if (mCommentDetailList.get(position).getReplyList() == null) {
            return 0;
        } else {
            return Math.max(mCommentDetailList.get(position).getReplyList().size(), 0);
        }

    }

    //返回group的实际数据，这里指的是当前评论数据
    @Override
    public Object getGroup(int groupPosition) {
        return mCommentDetailList.get(groupPosition);
    }

    //返回group中某个child的实际数据，这里指的是当前评论的某个回复数据
    @Override
    public Object getChild(int i, int i1) {
        return mCommentDetailList.get(i).getReplyList().get(i1);
    }

    //返回分组的id，一般将当前group的位置传给它
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    //返回分组中某个child的id，一般也将child当前位置传给它
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return getCombinedChildId(groupPosition, childPosition);
    }

    //表示分组和子选项是否持有稳定的id
    @Override
    public boolean hasStableIds() {
        return true;
    }

    //返回group的视图，一般在这里进行一些数据和视图绑定的工作
    // 一般为了复用和高效，可以自定义ViewHolder
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final GroupHolder groupHolder;
        //holder与布局
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.comment_item, parent, false);
            groupHolder = new GroupHolder(convertView);
            convertView.setTag(groupHolder);//setTag()？？
        } else {
            groupHolder = (GroupHolder) convertView.getTag();
        }
        //传递数据。list数据从哪来？没有单例的话，网络请求？
        // 中间那堆是啥？？intologo，估计是关于头像的？
        groupHolder.comment_name.setText(mCommentDetailList.get(groupPosition).getCommentName());
        groupHolder.comment_content.setText(mCommentDetailList.get(groupPosition).getComment());

        return convertView;

    }

    //返回分组中child的视图
    // 第一个参数是当前group所在的位置，第二个参数是当前child所在位置
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ChildHolder childHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.comment_reply_item,parent,false);
            childHolder = new ChildHolder(convertView);
            convertView.setTag(childHolder);
        }else{
            childHolder = (ChildHolder) convertView.getTag();
        }

        String replyUser = mCommentDetailList.get(groupPosition).getReplyList().get(childPosition).getReplyName();
        if (!TextUtils.isEmpty(replyUser)) {//??有待探索
            childHolder.reply_name.setText(replyUser + ":");
        }

        childHolder.reply_content.setText(mCommentDetailList.get(groupPosition).getReplyList().get(childPosition).getReply());

        return convertView;
    }

    //表示分组中的child是否可以选中
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    private class GroupHolder {
        //private CircleImageView logo;这是什么操作？？
        private ImageView comment_logo;
        private TextView comment_name, comment_content, comment_date;

        public GroupHolder(View view) {
            comment_name = (TextView) view.findViewById(R.id.comment_name);
            comment_date = (TextView) view.findViewById(R.id.comment_date);
            comment_content = (TextView) view.findViewById(R.id.comment_content);
            comment_logo = (ImageView) view.findViewById(R.id.comment_logo);
        }
    }

    private class ChildHolder {
        private TextView reply_name, reply_content;

        public ChildHolder(View view) {
            reply_name = (TextView) view.findViewById(R.id.reply_name);
            reply_content = (TextView) view.findViewById(R.id.reply_content);
        }
    }
/*评论成功后插入一条数据*/
    public void addTheCommentData(CommentDetail comment) {
        if (comment != null){
            mCommentDetailList.add(comment);
            notifyDataSetChanged();//BaseExpandableListAdapter用在更新数据集
        }else {
            throw new IllegalArgumentException("评论数据为空！");
        }
    }
/*回复成功后插入一条数据*/
    public void addTheReplyData(ReplyDetail replyDetail,int groupPosition) {
        //回复不为空，刷新数据:评论列表
        if (replyDetail != null){
            Log.e(TAG,"addTheReplyData：>>>该刷新回复列表了：" + replyDetail.toString());
            if (mCommentDetailList.get(groupPosition).getReplyList() != null){
                mCommentDetailList.get(groupPosition).getReplyList().add(replyDetail);
            }else {
                List<ReplyDetail> replyList = new ArrayList<>();
                replyList.add(replyDetail);
                mCommentDetailList.get(groupPosition).setReplyList(replyList);
            }
            notifyDataSetChanged();
        }else {
            throw new IllegalArgumentException("恢复数据为空！");
        }
    }
}

