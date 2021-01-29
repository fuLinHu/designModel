package com.elk.demo.searchentity;

import com.elk.demo.searchentity.enumentity.SearchDataType;
import com.elk.demo.searchentity.enumentity.SearchType;
import com.elk.demo.searchentity.fieldparam.MatchField;
import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/1/25 17:14
 * @Version V1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchParam {

    //===============SearchRequest  赋值的参数  start=========================//

    //指定 路由键  根据公式 shard_num = hash(_routing) % num_primary_shards  找到对应的  分片上
    private String[] routing;
    /**
     1、query and fetch
     向索引的所有分片（shard）都发出查询请求，各分片返回的时候把元素文档（document）和计算后的排名信息一起返回。这种搜索方式是最快的。因为相比下面的几种搜索方式，这种查询方法只需要去shard查询一次。但是各个shard返回的结果的数量之和可能是用户要求的size的n倍。
     2、query then fetch（默认的搜索方式）
     答案：是也不是。默认情形下，ES会使用一个称之为Query then fetch的搜索类型。它运作的方式如下：
     发送查询到每个shard
     找到所有匹配的文档，并使用本地的Term/Document Frequency信息进行打分
     对结果构建一个优先队列（排序，标页等）
     返回关于结果的元数据到请求节点。注意，实际文档还没有发送，只是分数
     来自所有shard的分数合并起来，并在请求节点上进行排序，文档被按照查询要求进行选择
     最终，实际文档从他们各自所在的独立的shard上检索出来
     结果被返回给用户
     这个系统一般是能够良好地运作的。大多数情形下，你的索引有足够的文档来平滑Term/Document frequency统计信息。因此，尽管每个shard不一定拥有完整的关于整个cluster的frequency信息，结果仍然足够好，因为fequency在每个地方基本上是类似的。
     但是在我们开头提起的那个查询，默认搜索类型有时候会失败。
     3、DFS query and fetch
     这种方式比第一种方式多了一个初始化散发(initial scatter)步骤，有这一步，据说可以更精确控制搜索打分和排名。
     4、DFS query then fetch
     在上篇文章中，我们默认建立了一个索引，ES通常使用5个shard。接着插入了5个文档进入索引，向ES发送请求返回相关结果和准确的分数。其结果并不是很公平，对吧？
     这是由于默认的搜索类型导致的，每个shard仅仅包含一个或者两个文档（ES使用hash确保随机分布）。当我们要求ES计算分数时候，每个shard仅仅拥有关于五个文档的一个很窄的视角。所以分数是不准确的。
     幸运的是，ES并没有让你无所适从。如果你遇到了这样的打分偏离的情形，ES提供了一个称为“DFS Query Then Fetch”。这个过程基本和Query Then Fetch类型，除了它执行了一个预查询来计算整体文档的frequency。
     预查询每个shard，询问Term和Document frequency
     发送查询到每隔shard
     找到所有匹配的文档，并使用全局的Term/Document Frequency信息进行打分
     对结果构建一个优先队列（排序，标页等）
     返回关于结果的元数据到请求节点。注意，实际文档还没有发送，只是分数
     来自所有shard的分数合并起来，并在请求节点上进行排序，文档被按照查询要求进行选择
     最终，实际文档从他们各自所在的独立的shard上检索出来
     结果被返回给用户
     如果我们使用这个新的搜索类型，那么获得的结果更加合理了（这些都一样的）：
     DSF是什么缩写？初始化散发是一个什么样的过程？
     从es的官方网站我们可以指定，初始化散发其实就是在进行真正的查询之前，先把各个分片的词频率和文档频率收集一下，然后进行词搜索的时候，各分片依据全局的词频率和文档频率进行搜索和排名。显然如果使用DFS_QUERY_THEN_FETCH这种查询方式，效率是最低的，因为一个搜索，可能要请求3次分片。但，使用DFS方法，搜索精度应该是最高的。
     至于DFS是什么缩写，没有找到相关资料，这个D可能是Distributed，F可能是frequency的缩写，至于S可能是Scatter的缩写，整个单词可能是分布式词频率和文档频率散发的缩写。
     总结一下，从性能考虑QUERY_AND_FETCH是最快的，DFS_QUERY_THEN_FETCH是最慢的。从搜索的准确度来说，DFS要比非DFS的准确度更高。
     当然，更好准确性不是免费的。预查询本身会有一个额外的在shard中的轮询，这个当然会有性能上的问题（跟索引的大小，shard的数量，查询的频率等）。在大多数情形下，是没有必要的，拥有足够的数据可以解决这样的问题。但是有时候，你可能会遇到奇特的打分场景，在这些情况中，知道如何使用DFS query then fetch去进行搜索执行过程的微调还是有用的。
     {@link SearchType}

     **/
    private String searchType;

    //===============SearchRequest  赋值的参数 end=========================//




    //实现的dao类型
    @NotNull
    private SearchDataType searchDataType;
    //是否有返回结果
    @Builder.Default
    private boolean fetchSource = true;
    //查询结果包含得字段 当 includeFields 为空得时候代表全包含
    private String[] includeFields;
    //查询结果不包含得字段 当 excludeFields 为空得时候代表全包含
    private String[] excludeFields ;
    //需要搜索得索引  如果为null  代表搜索所有
    private String[] indexName;
    //分页查询，如果为null 则不分页
    private SearchPage searchPage;
    // highlightParam 为null 则没有任何字段需要高亮
    private HighlightParam highlightParam;
    //需要排序的字段
    private SortParam[] sortParam;
    //搜索类型
    //query_and_fetch
    //query_then_fetch
    //dfs_query_and_fetch
    //dfs_query_then_fetch

    /**
     * @Author 付林虎
     * @Description //TODO  最少匹配  同 {@link MatchField}
     * @Date 2021/1/28 10:56
     * @Param
     * @Version V1.0
     * @return
     **/
    private Object minimum_should_match;

    /**
     * @Author 付林虎
     * @Description //TODO  dis_max 类型特有的参数
     * @Date 2021/1/28 13:46
     * @Param
     * @Version V1.0
     * dis_max，只是取分数最高的那个query的分数而已，完全不考虑其他query的分数，这种一刀切的做法，可能导致在有其他query的影响下，
     * score不准确的情况，这时为了使用结果更准确，最好还是要考虑到其他query的影响
     * tie_breaker参数的意义，将其他query的分数乘以tie_breaker，然后综合考虑后与最高分数的那个query的分数综合在一起进行计算，这样做除了取最高分以外，
     * 还会考虑其他的query的分数。tie_breaker的值，设置在在0~1之间，是个小数就行，没有固定的值
     **/
    private Float tie_breaker;

}
